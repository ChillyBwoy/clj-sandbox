(ns sandbox.apps.particle-system
  (:require [sandbox.lib.canvas :as canvas]
            [sandbox.lib.geom.vector2d :as v2d]
            [sandbox.lib.geom.emitter :refer [emitter emit-particle-range]]
            [sandbox.lib.geom.point :as point]))

(enable-console-print!)


(def ^:private context (canvas/init "display"))

(def ^:private app-state (atom {:width (.-innerWidth js/window)
                                :height (.-innerHeight js/window)
                                :emitters [(emitter (v2d/create 100 230) (v2d/from-angle 0 2))]
                                :particles (vec (map point/create (range 10)))}))

(defn- draw-particle [{{x :x y :y} :pos color :color size :size :as p}]
  (set! (.-fillStyle context) color)
  (.fillRect context x y size size)
  p)


(defn- is-in-bounds [{{x :x y :y} :pos} bounds-x bounds-y]
  (and (> x 0) (< x bounds-x) (> y 0) (< y bounds-y)))


(defn- plot-particles [particles bounds-x bounds-y]
  (let [visible-particles (vec (filter #(is-in-bounds % bounds-x bounds-y) particles))]

    (let [new-particles (vec (map point/move visible-particles))]
      (swap! app-state assoc :particles new-particles))))


(defn- add-particles [emitters particles max-particles]
  (when (< (count particles) max-particles)
    (let [new-particles (vec (flatten (vec (map emit-particle-range emitters))))]
      (swap! app-state assoc :particles (vec (flatten (conj particles new-particles)))))))


(defn- render []
  (.requestAnimationFrame js/window render)
  (let [{:keys [width height emitters particles]} @app-state]
    (.clearRect context 0 0 width height)
    (add-particles emitters particles 200)
    (plot-particles particles width height)
    (doseq [p particles]
      (print p))
    (map draw-particle particles)))

(defn ^:export run []
  (render))
