(ns roller-ball.api.router
  (:use compojure.core)
  (:require [ring.middleware.json :refer [wrap-json-response wrap-json-body]])
  (:require [ring.middleware.multipart-params :refer [wrap-multipart-params]])
  (:require [roller-ball.middleware.wrap-favicon-not-found :refer [wrap-favicon-not-found]])
  (:require [roller-ball.middleware.wrap-disable-cors :refer [wrap-disable-cors]])
  (:require [roller-ball.api.GET-health-check :refer [GET-health-check-handler]])
  (:require [roller-ball.api.GET-objects-at-level :refer [GET-objects-at-level]])
  (:require [roller-ball.api.POST-upload-object :refer [POST-upload-object-handler]])
  (:require [roller-ball.api.POST-new-upload :refer [POST-new-upload-handler]]))

(defroutes app-routes
   (GET "/" [] GET-health-check-handler)
   (GET "/tree" [] GET-objects-at-level)
   (POST "/new" [] POST-new-upload-handler)
   (POST "/upload" [] POST-upload-object-handler))

(def api
  (-> app-routes
      wrap-json-response
      wrap-json-body
      wrap-multipart-params
      wrap-disable-cors
      wrap-favicon-not-found))