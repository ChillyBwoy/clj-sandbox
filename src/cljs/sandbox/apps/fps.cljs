(ns sandbox.apps.fps
  (:require [sandbox.lib.canvas :as canvas]
            [sandbox.lib.geom.vector2d :as v2d]))


(def ^:private app-state (atom {:width (.-innerWidth js/window)
                                :height (.-innerHeight js/window)
                                :context (canvas/init "display")}))


(defn- render [])


(defn ^:export run []
  (render))
