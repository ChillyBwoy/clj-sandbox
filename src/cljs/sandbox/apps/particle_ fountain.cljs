(ns sandbox.apps.particle-fountain
  (:require [sandbox.lib.canvas :refer [width height] :as canvas]
            [sandbox.lib.geom.point :as point]))

(enable-console-print!)

(def ^:private app-state (atom {:width (.-innerWidth js/window)
                                :height (.-innerHeight js/window)
                                :context (canvas/init "display")
                                :particles (vec (map point/create (range 10)))}))


(defn- render []
  (.requestAnimationFrame js/window render)
  (let [{:keys [width height context particles]} @app-state]
    (.clearRect context 0 0 width height)))


(defn ^:export run []
  (render))
