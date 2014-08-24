(ns sandbox.lib.geom.point
  (:require [sandbox.lib.geom.vector2d :as v2d]))


(defn create
  ([] (create (v2d/create) (v2d/create) (v2d/create) (v2d/create)))
  ([pos] (create pos (v2d/create) (v2d/create) (v2d/create)))
  ([pos old-pos] (create pos old-pos (v2d/create) (v2d/create)))
  ([pos old-pos velocity] (create pos old-pos velocity (v2d/create)))
  ([pos old-pos velocity acceleration]
   {:pos pos
    :old-pos old-pos
    :velocity velocity
    :acceleration acceleration
    :size 2
    :color "#880088"}))


(defn move [{:keys [pos velocity acceleration] :as p}]
  (let [new-velocity (v2d/add velocity acceleration)
        new-pos (v2d/add pos new-velocity)]
    (assoc p :velocity new-velocity :pos new-pos)))


(defn integrate [{:keys [x y old-x old-y] :as p} damping]
  (let [velocity-x (* (- x old-x) damping)
        velocity-y (* (- y old-y) damping)]
    (assoc p :x (+ x velocity-x)
             :y (+ y velocity-y)
             :old-x x
             :old-y y)))


(defn bounce
  [{:keys [pos velocity] :as p} {:keys [width height] :as dim}])
