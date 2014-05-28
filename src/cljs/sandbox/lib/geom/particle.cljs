(ns sandbox.lib.geom.particle
  (:require [sandbox.lib.geom.vector2d :refer [vector2d add]]))

(enable-console-print!)

(defn particle
  ([] (particle (vector2d) (vector2d) (vector2d)))
  ([pos] (particle pos (vector2d) (vector2d)))
  ([pos velocity] (particle pos velocity (vector2d)))
  ([pos velocity acceleration]
   {:pos pos
    :velocity velocity
    :acceleration acceleration
    :size 2
    :color "#F0F"}))

(defn move-particle [{:keys [pos velocity acceleration] :as p}]
  (let [new-velocity (add velocity acceleration)
        new-pos (add pos new-velocity)]
    (assoc p :velocity new-velocity :pos new-pos)))
