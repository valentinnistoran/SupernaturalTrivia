import SQLite from 'react-native-sqlite-storage';

const db = SQLite.openDatabase(
    {
      name: 'AppDatabase.db',
      location: 'default',
    },
    () => { },
    error => { console.error(`Error opening database: ${error.message}`); }
  );

  db.transaction(tx => {
    tx.executeSql(
      `CREATE TABLE IF NOT EXISTS Questions (
        id INTEGER PRIMARY KEY,
        question TEXT,
        answer_a TEXT,
        answer_b TEXT,
        answer_c TEXT,
        answer_d TEXT
      );`,
      [],
      () => {
        // Table created successfully
      },
      (tx, error) => {
        console.error(`Error creating table: ${error.message}`);
      }
    );
  });

  