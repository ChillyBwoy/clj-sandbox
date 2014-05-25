(ns sandbox.lib.geom.emitter
  (:require [sandbox.lib.math :as math]
            [sandbox.lib.geom.vector2d :as v2]
            [sandbox.lib.geom.particle :as pt]))

(defn emitter
  ([pos velocity]
   (emitter pos velocity (/ math/PI 10) 10))
  ([pos velocity spread]
   (emitter pos velocity spread 10))
  ([pos velocity spread rate]
   {:pos pos
    :velocity velocity
    :spread spread
    :rate rate
    :size 5
    :color "#C0C"}))

(defn emit [{:keys [pos velocity spread] :as em}]
  (let [angle (+ (v2/angle velocity) (- spread (* (rand) spread 2)))
        magnitude (v2/magnitude velocity)
        new-velocity (v2/from-angle angle magnitude)
        new-pos (v2/vector2d (:x pos) (:y pos))]
    (pt/particle new-pos new-velocity)))

(defn emit-range [{:keys [rate] :as em}]
  (vec (map #(emit em) (range rate))))
