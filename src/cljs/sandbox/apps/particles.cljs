(ns sandbox.apps.particles
  (:require [sandbox.lib.canvas :refer [width height] :as canvas]
            [sandbox.lib.color :refer [rand-rgb]]
            [sandbox.lib.math :refer [sqrt]]))

(enable-console-print!)

(def context (canvas/init "display"))
(def damping 0.999)
(def max-particles 500)
(def line-width 2)


(defn create-particle []
  (let [w (width)
        h (height)
        x (rand w)
        y (rand h)
        color (rand-rgb)]
    {:x x, :y y, :old-x x, :old-y y, :color color}))

(def ^:private app-state (atom {:mouse {:x (* (width) 0.5)
                                        :y (* (height) 0.5)}
                                :particles (vec (map create-particle (range max-particles)))}))


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
  (let [w (width)
        h (height)]
    (and (> x 0) (< x w) (> y 0) (< y h))))

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
  (let [{:keys [particles mouse]} @app-state
        w (width)
        h (height)]
    (.clearRect context 0 0 w h)
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


(defn ^:export run []
  (.addEventListener js/window "mousemove" mousemove-handler)
  (render))
