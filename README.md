# Book manager for BotsCrew

This is a simple book management system, which uses Hibernate in its data layer.

## Installation

To run this project you should simply clone the repository and build it with maven. Besides that you will need to modify hibernate.cfg.xml -- set your database server setting in the hibernate.connection group.
After this, you are ready to run the project.

#### Important note
To run this application you should start it from your system terminal, not from the IDE`s one.

#### Manual database creation

If your database user which is set in hibernate.connection properties doesn`t have rights to modify db schema you should manually create the database whith following SQL requests.

##### Book
```sql
CREATE TABLE `Book` (
  `book_id` bigint(20) NOT NULL,
  `book_name` varchar(250) NOT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`book_id`),
  KEY `FK5gbo4o7yxefxivwuqjichc67t` (`author_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
```

#### Author

```sql
CREATE TABLE `Author` (
  `author_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`author_id`),
  UNIQUE KEY `UK_soak1hrrvg12k041wmdawe6t5` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

```

And after this set the hibernate.hbm2ddl.auto property to none.

## Design

The bookmanager uses a database with two tables: Book and Author through it`s really simple, it allows to modify the system quite easily. For the same reason it has a complete DAO-Service-Controller architecture.

## Usage

In this system each book is represented with just 2 fields: its name and author. Authors are set by their names.

As for now this system supports following commands:
- add a book
- remove a book
- modify books 1) author 2) name
- get 1) all books 2) all books for a given author
- exit

To add a book user should enter a following command: "add author_name book_name". book_name and author_name can be as single words, as groups of words (grouped by " or ').

To get a list of all books, user can use "list" commands. Also, it`s possible to get a list of books for a particular author by typing in "list author_name".

To remove a book user should use "remove book_name" command. If there`s more then one book with given name (e.g. with different authors) user will get a list of all books with given name and will choose one to delete.

To edit a book, user should type "edit book_name" command. Then he can choose if he wants to change name of the book or its author. In case when there`re different books with the same name, this command gives the same list as the 'remove' one.

To stop using the app and exit from it, user simply enters "exit".

## Contribution

Please fill free, to modify this application as it will be published as a study material on my blog (http://dubovyksergey.wordpress.com) ASAP.

## License

All the source code is published under the MIT License. So, feel free to use it.

MIT License

Copyright (c) 2017 Sergey Dubovyk

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
