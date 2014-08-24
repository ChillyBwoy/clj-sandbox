(ns sandbox.apps.fps
  (:require [sandbox.lib.canvas :as canvas]
            [sandbox.lib.geom.vector2d :refer [vector2d]]))




(defn player [{x :x, y :y} direction]
  {:pos (vector2d x y)
   :direction direction})


(defn world [size]
  {:size size
   :wall})


(defn ^:export run []
  )
