(ns sandbox.apps.particles
  (:require [sandbox.lib.canvas :as canvas])
  (:use [sandbox.lib.color :only [rand-rgb]]
        [sandbox.lib.math :only [sqrt]]))

(enable-console-print!)

(def context (canvas/init "display"))
(def damping 0.999)
(def max-particles 500)
(def line-width 2)

(def app-state (atom {:width (.-innerWidth js/window)
                      :height (.-innerHeight js/window)}))


(defn create-particle []
  (let [width (:width @app-state)
        height (:height @app-state)
        x (rand width)
        y (rand height)
        color (rand-rgb)]
    {:x x, :y y, :old-x x, :old-y y, :color color}))

(swap! app-state assoc :mouse {:x (* (:width @app-state) 0.5)
                               :y (* (:height @app-state) 0.5)})
(swap! app-state assoc :particles (vec (map create-particle (range max-particles))))


(defn integrate [{:keys [x y old-x old-y] :as particle}]
  (let [velocity-x (* (- x old-x) damping)
        velocity-y (* (- y old-y) damping)]
    (assoc particle :x (+ x velocity-x)
                    :y (+ y velocity-y)
                    :old-x x
                    :old-y y)))

(defn attract [pos-x pos-y {:keys [x y] :as particle}]
  (let [dx (- pos-x x)
        dy (- pos-y y)
        distance (sqrt (+ (* dx dx) (* dy dy)))
        new-x (+ x (/ dx distance))
        new-y (+ y (/ dy distance))]
    (assoc particle :x new-x :y new-y)))

(defn is-visible [x y]
  (let [{:keys [width height]} @app-state]
    (and (> x 0) (< x width) (> y 0) (< y height))))

(defn draw [ctx {:keys [x y old-x old-y color]}]
  (do
    (set! (.-strokeStyle ctx) color)
    (set! (.-lineWidth ctx) line-width)
    (.beginPath ctx)
    (.moveTo ctx old-x old-y)
    (.lineTo ctx x y)
    (.stroke ctx)))

(defn render []
  (.requestAnimationFrame js/window render)
  (let [{:keys [width height particles mouse]} @app-state]
    (.clearRect context 0 0 width height)
    (dotimes [n max-particles]
      (let [particle (nth particles n)
            x (:x mouse)
            y (:y mouse)
            p (integrate (attract x y particle))]
        (when (is-visible (:x p) (:y p))
          (draw context p))
        (swap! app-state assoc-in [:particles n] p)))))

(defn mousemove-handler [event]
  (swap! app-state assoc :mouse {:x (.-clientX event)
                                 :y (.-clientY event)}))

(defn windowresize-handler [event]
  (let [w (.-innerWidth js/window)
        h (.-innerHeight js/window)]
    (swap! app-state assoc :width w :height h)))

(defn ^:export run []
  (.addEventListener js/window "mousemove" mousemove-handler)
  (.addEventListener js/window "resize" windowresize-handler)
  (render))
