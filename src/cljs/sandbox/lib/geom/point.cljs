(ns sandbox.lib.geom.point
  (:require [sandbox.lib.geom.vector2d :as v2d]))

(comment
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


(defn integrate [{:keys [pos old-pos] :as p} damping]
  (let [velocity-x (* (- (:x pos) (:x old-pos)) damping)
        velocity-y (* (- (:y pos) (:y old-pos)) damping)]
    (assoc p :pos (v2d/create velocity-x velocity-y)
             :old-pos pos)))


(defn attract [{:keys [pos] :as p} to-pos]
  (let [new-pos (v2d/sub to-pos pos)
        distance (v2d/distance new-pos)
        x (+ (:x pos) (/ (:x new-pos) distance))
        y (+ (:y pos) (/ (:y new-pos) distance))]
    (assoc p :pos (v2d/create x y))))


(defn bounce
  [{:keys [pos velocity] :as p} {:keys [width height] :as dim}])
)
