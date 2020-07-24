(ns mdb-to-csv.core
  (:gen-class)
  (:require
   [clojure.walk :as walk]
   [clojure.data.csv :as csv]
   [clojure.java.io :as io]
  )
  (:import
   com.healthmarketscience.jackcess.DatabaseBuilder
   com.healthmarketscience.jackcess.Table
   com.healthmarketscience.jackcess.Table$ColumnOrder
   com.healthmarketscience.jackcess.impl.RowImpl
   java.io.File
   java.util.ArrayList
   java.util.LinkedHashMap))

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

(defn rows-data
  "Returns a sequence of values-rows in the table that optionally match a pattern {column value}"
  ([table]
   (doall
   (for [row (iterator-seq (.iterator table))]
     (map #(if (number? %)
             (clojure.string/replace % #"\." ",")
             %) (.values row))))))

(defn rows-id
  "Returns a sequence of keys-rows in the table that optionally match a pattern {column value}"
  ([table]
   (doall
    (for [row (iterator-seq (.iterator table))]
      (.keySet row)))))

(defn point-to-coma [coll-v]
  (for [row (iterator-seq (.iterator table))]
    (map #(if (number? %)
            (clojure.string/replace % #"\." ",")
            %) (.values row)))
  
  )

(defn make-csv [database-path table-name]
  "Make a csv file based on id and data of selected table"
  (let [database-acces (atom (open-db database-path))
        info-display (.setColumnOrder @database-acces Table$ColumnOrder/DISPLAY)
        table-access (table @database-acces table-name)
        id-table (into #{} (rows-id table-access))
        data-table (rows-data table-access)
        complete-table (concat id-table data-table)]
    (with-open [writer (io/writer (str table-name ".csv"))]
      (csv/write-csv writer
                     complete-table :separator \;))))


