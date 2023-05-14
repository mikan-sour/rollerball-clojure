(ns roller-ball.api.router
  (:use compojure.core)
  (:require [ring.middleware.json :refer [wrap-json-response]])
  (:require [ring.middleware.multipart-params :refer [wrap-multipart-params]])
  (:require [roller-ball.middleware.wrap-favicon-not-found :refer [wrap-favicon-not-found]])
  (:require [roller-ball.api.GET-health-check :refer [GET-health-check-handler]])
  (:require [roller-ball.api.POST-upload-object :refer [POST-upload-object-handler]]))

(defroutes app-routes
   (GET "/" [] GET-health-check-handler)
   (POST "/upload" [] POST-upload-object-handler))

(def api
  (-> app-routes
      wrap-json-response
      wrap-multipart-params
      wrap-favicon-not-found))