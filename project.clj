(defproject mdb-to-csv "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [com.healthmarketscience.jackcess/jackcess "3.0.1"]
                 [commons-logging/commons-logging "1.1.3"]
                 [commons-lang/commons-lang "2.6"]
                 [org.apache.poi/poi "3.9"]
                 [log4j/log4j "1.2.7"]
                 [org.clojure/data.csv "1.0.0"]
                  [clojess "0.3.1"]]
  :main ^:skip-aot mdb-to-csv.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
