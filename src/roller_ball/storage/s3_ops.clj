(ns roller-ball.storage.s3-ops
  (:require [roller-ball.storage.s3-consts :refer [s3-consts]])
  (:use
        [amazonica.aws.s3]))

(def bucket-name (get s3-consts :bucket-name))

;(defn list-all-objects
;  "list all objects at a given path in storage"
;  [path]
;  (list-objects-v2
;    {
;     :bucket-name bucket-name
;     :prefix path
;     }))

(defn upload-new-object
  "upload to storage"
  [key file]
  (put-object
    :bucket-name bucket-name
    :key key
    :metadata {:server-side-encryption "AES256"}
    :file file
    )
  )

;(defn get-existing-object
;  "gets an object from storage"
;  [key]
;  (get-object
;    :bucket-name bucket-name
;    :key key))
