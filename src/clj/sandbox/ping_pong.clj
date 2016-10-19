(ns sandbox.ping-pong
  (:require [clojure.core.async :refer [<! >! >!! chan go-loop close!]]))

(def ping (chan))
(def pong (chan))

(go-loop []
  (let [msg (<! ping)]
    (when (= msg :ping)
      (println msg)
      (>! pong :pong)
      (Thread/sleep 1000))
    (recur)))


(go-loop []
  (let [msg (<! pong)]
    (when (= msg :pong)
      (println msg)
      (>! ping :ping)
      (Thread/sleep 1000))
    (recur)))

(>!! ping :ping)

(close! ping)
(close! pong)
