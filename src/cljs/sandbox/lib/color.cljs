(ns sandbox.lib.color)

(defn rand-rgb []
  (let [r (rand-int 255)
        g (rand-int 255)
        b (rand-int 255)]
    (str "rgb(" r "," g "," b ")")))
