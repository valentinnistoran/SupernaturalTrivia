import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
//import db from './init';
import * as React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import HomePage from './navigation/HomePage'; // Import your screen components
import InfoPage from './navigation/InfoPage';
import ProfilePage from './navigation/ProfilePage';
import SettingsPage from './navigation/SettingsPage';

const Tab = createBottomTabNavigator();

export default function App() {
  // return (
  //   <View style={styles.container}>
  //     <Text>Open up App.js to start working on your app!</Text>
  //     <StatusBar style="auto" />
  //   </View>
  // );
  return (
    <NavigationContainer>
      <Tab.Navigator>
        <Tab.Screen name="Home" component={HomePage} />
        <Tab.Screen name="Info" component={InfoPage} />
        <Tab.Screen name="Profile" component={ProfilePage} />
        <Tab.Screen name="Settings" component={SettingsPage} />
      </Tab.Navigator>
    </NavigationContainer>
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
