(ns roller-ball.api.POST-new-upload
  (:require [ring.util.response :refer [response]])
  (:require [roller-ball.repo.couch-db-repo :refer [new-doc]]))

(defn get-body
  "gets the body from the post"
  [request]
  (get request :body))

(defn POST-new-upload-handler [request]
  (try
    (def res
      (-> request
          get-body
          new-doc))
    (response {:result res})
    (catch Exception e
      (println (.getMessage e))
      )
    )
  )

