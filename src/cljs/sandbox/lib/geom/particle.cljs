(ns sandbox.lib.geom.particle
  (:require [sandbox.lib.geom.vector2d :as v2]))


(defn particle
  ([]
   (particle (v2/vector2d 0 0) (v2/vector2d 0 0) (v2/vector2d 0 0)))
  ([pos]
   (particle pos (v2/vector2d 0 0) (v2/vector2d 0 0)))
  ([pos velocity]
   (particle pos velocity (v2/vector2d 0 0) "#FFF"))
  ([pos velocity acceleration]
   (particle pos velocity acceleration "#FFF"))
  ([pos velocity acceleration color]
   {:pos pos
    :velocity velocity
    :acceleration acceleration
    :color color}))

(defn move [{:keys [pos velocity acceleration] :as p}]
  (let [new-velocity (v2/add velocity acceleration)
        new-pos (v2/add pos new-velocity)]
    (assoc p :velocity new-velocity :pos new-pos)))
