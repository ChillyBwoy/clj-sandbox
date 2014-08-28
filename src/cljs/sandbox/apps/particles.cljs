(ns sandbox.apps.particles
  (:require [sandbox.lib.canvas :as canvas]
            [sandbox.lib.color :refer [rand-rgb]]
            [sandbox.lib.geom.vector2d :refer [vector2d]]
            [sandbox.lib.math :refer [sqrt]]))

(def max-particles 1500)


(defn- create-particle []
  (let [x (rand (canvas/width))
        y (rand (canvas/height))]
    {:pos (vector2d x y)
     :old-pos (vector2d x y)
     :color (rand-rgb)
     :size 2}))

(def ^:private app-state (atom {:particles (vec (map create-particle (range max-particles)))}))


(defn- integrate [{[x y] :pos
                   [old-x old-y] :old-pos :as particle}]
  (let [velocity-x (* (- x old-x) 0.999)
        velocity-y (* (- y old-y) 0.999)
        new-x (+ x velocity-x)
        new-y (+ y velocity-y)]
    (assoc particle :pos (vector2d new-x new-y)
                    :old-pos (vector2d x y))))


(defn- attract [{[x y] :pos
                 [old-x old-y] :old-pos :as particle} [pos-x pos-y]]
  (let [dx (- pos-x x)
        dy (- pos-y y)
        distance (sqrt (+ (* dx dx) (* dy dy)))
        new-x (+ x (/ dx distance))
        new-y (+ y (/ dy distance))]
    (assoc particle :pos (vector2d new-x new-y))))


(defn- is-visible [{[x y] :pos} [width height]]
  (and (> x 0) (< x width) (> y 0) (< y height)))


(defn- draw [ctx {[x y] :pos
                  [old-x old-y] :old-pos
                  color :color
                  size :size}]
  (set! (.-strokeStyle ctx) color)
  (set! (.-lineWidth ctx) size)
  (.beginPath ctx)
  (.moveTo ctx old-x old-y)
  (.lineTo ctx x y)
  (.stroke ctx))


(defn- move-particle [particle context mouse bounds]
  (let [p (integrate (attract particle mouse))]
    (when (is-visible p bounds)
      (draw context p))
    p))


(defn- draw-particles [particles context mouse bounds]
  (vec (map #(move-particle % context mouse bounds) particles)))


(defn render []
  (.requestAnimationFrame js/window render)
  (let [{:keys [context particles]} @app-state
        width (canvas/width)
        height (canvas/height)
        mouse-pos (canvas/mouse-position)]
    (.clearRect context 0 0 width height)
    (let [new-particles (draw-particles particles context mouse-pos [width height])]
      (swap! app-state assoc :particles new-particles))))

(defn ^:export run []
  (swap! app-state assoc :context (canvas/init "display"))
  (render))
