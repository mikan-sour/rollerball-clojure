(ns roller-ball.repo.couch-db-consts)

(def db-url (format "http://%s:%s@%s:%d/%s"
                    "couchdb_user"
                    "couchdb_password"
                    "localhost"
                    5984
                    "my_db"))

