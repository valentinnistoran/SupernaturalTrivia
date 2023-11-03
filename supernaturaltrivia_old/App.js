import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
//import db from './init';

export default function App() {
  return (
    <View style={styles.container}>
      <Text>Open up App.js to start working on your app!</Text>
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  questionContainer: {
    backgroundColor: '@DDDDDD',
    padding: 10,
    margin: 10,
    borderRadius: 5,
  },
  optionStyle:{
    color: 'yellow',
    padding: 5,
  },
  optionContainer:{
    borderColor: 'black',
    borderWidth: 2,
    marginTop: 10,
  }

});
