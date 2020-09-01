# mdb-to-csv

Export csv from Microsoft Access Database, wrapping Jackcess.

## Where

[![Clojars Project](https://img.shields.io/clojars/v/mdb-to-csv.svg)](https://clojars.org/mdb-to-csv)

## Why

The purpose of mdb-to-csv is to convert tables, rows , and single data from access database into a csv format.
## Usage
Open a database from a file and select the table to export as csv to a path:
``` clj
=> (make-csv "path/to/file.db" "name-table)
```


## License

Copyright © 2020

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
