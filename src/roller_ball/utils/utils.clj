(ns roller-ball.utils.utils
  (:require [clojure.string :as s]))

(defn add [a b]
  (+ a b))

(defn does-not-have-slash-in-name
  "use this to ensure there's no slash in a name for an obj name
  returns bool"
  [obj-name]
  s/includes? obj-name "/"
  )