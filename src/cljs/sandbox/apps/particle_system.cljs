(ns sandbox.apps.particle-system
  (:require [sandbox.lib.geom.particle :as pt]
            [sandbox.lib.geom.emitter :as em]
            [sandbox.lib.geom.vector2d :as v2]
            [sandbox.lib.math :as math]))

(def display (.getElementById js/document "display"))
(def context (.getContext display "2d"))
(def max-particles 100)
(def particle-size 2)

(def app-state (atom {:width (.-innerWidth js/window)
                      :height (.-innerHeight js/window)
                      :particles []
                      :emitters []}))

(defn init []
  (let [{:keys [width height]} @app-state]
    (do
      (set! (.-width display) width)
      (set! (.-height display) height)
      (swap! app-state assoc :emitters
             [(em/emitter (v2/vector2d 100 230) (v2/from-angle 0 2))]))))


(defn add-particles []
  (let [{:keys [particles emitters]} @app-state]
    (when (< (count particles) max-particles)
      (let [new-particles (vec (flatten (map em/emit-range emitters)))]
        (swap! app-state assoc :particles (flatten (conj particles new-particles)))))))

(defn plot-particles [bounds-x bounds-y]
  (let [new-particles (vec (map (fn [p]
                                  (let [{{x :x, y :y} :pos} p]
                                    (when (and (> x 0) (< x bounds-x) (> y 0) (< y bounds-y))
                                      (pt/move p)))) (:particles @app-state)))]
    (swap! app-state assoc :particles new-particles)))

(defn draw-circle [ctx obj]
  (let [{:keys [pos color size]} obj
        {:keys [x y]} pos]
    (set! (.-fillStyle ctx) color)
    (.beginPath ctx)
    (.arc ctx x y size 0 (* math/PI 2))
    (.closePath ctx)
    (.fill ctx)))

(defn draw-particles [ctx]
  (doseq [p (:particles @app-state)]
    (let [{:keys [pos color]} p]
      (do
        (set! (.-fillStyle ctx) color)
        (.fillRect ctx (:x pos) (:y pos) particle-size particle-size)))))

(defn update [width height]
  (add-particles)
  (plot-particles width height))

(defn render []
  ;;(.requestAnimationFrame js/window render)
  (let [{:keys [width height emitters]} @app-state]
    (.clearRect context 0 0 width height)
    (update width height)
    (doseq [em emitters]
      (draw-circle context em))
    (draw-particles context)))

(defn ^:export run []
  (init)
  (render))
