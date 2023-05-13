(ns roller-ball.core
  ;(:require [roller-ball.utils.utils :refer [add]])
  (:require [roller-ball.repo.couch-db-repo :refer [get-doc find-by-tag]]))

(defn -main [& args]
  (def all (find-by-tag "c"))
  (println all)
  (def doc (get-doc "01e85e532f28058d8c000c3fec004232"))
  (println (get doc :tags)))



