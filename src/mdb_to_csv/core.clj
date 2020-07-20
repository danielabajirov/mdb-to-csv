(ns mdb-to-csv.core
  (:gen-class)
  (:require [clojure.walk :as walk]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [clojess.core :as db])
  (:import
   com.healthmarketscience.jackcess.DatabaseBuilder
   com.healthmarketscience.jackcess.Table
   com.healthmarketscience.jackcess.Table$ColumnOrder
   com.healthmarketscience.jackcess.impl.RowImpl
   java.io.File))

(defn open-db
  "Opens a database file. Returns a Database object."
  [path]
  (DatabaseBuilder/open (File. path)))

(defn table-names
  "Returns a set of all tablenames in the database"
  [database]
  (.getTableNames database))

(defn table
  "Returns the table with given name from the db, or nil if not found"
  [database name]
  (.getTable database name))

(defn tables
  "Returns a set of all tables in the database"
  [database]
  (set
   (map (partial table database)
        (table-names database))))

(defn iteration->seq [iteration]
  (seq
   (reify java.lang.Iterable
     (iterator [this]
       (reify java.util.Iterator
         (hasNext [this] (.hasNext iteration))
         (next [this] (.next iteration))
         (remove [this] (.remove iteration)))))))


(defn rows
  "Returns a sequence of rows in the table."
  ([table]
   (doall
    (for [row (iterator-seq (.iterator table))]
      row)))
  )



;(def database-acces (open-db "./resources/BIMWelt.mdb"))
;; (.setColumnOrder database-acces Table$ColumnOrder/DISPLAY)


(def database-acces (atom (open-db "./resources/BIMWelt.mdb")))
(.setColumnOrder @database-acces Table$ColumnOrder/DISPLAY)
(def table-access (table @database-acces "Arcs"))
