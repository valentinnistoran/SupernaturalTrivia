import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { NavigationContainer } from '@react-navigation/native';
import { Ionicons } from '@expo/vector-icons';

import HomePage from './pages/HomePage.js';
import InfoPage from './pages/InfoPage.js';
import ProfilePage from './pages/ProfilePage.js';
import SettingsPage from './pages/SettingsPage.js';

const BottomNavigator = createBottomTabNavigator();

const CustomTabBar = ({ state, descriptors, navigation }) => {
  return (
    <View style={{ flexDirection: 'row', height: 80, backgroundColor: '#9F0000' }}>
      {state.routes.map((route, index) => {
        const { options } = descriptors[route.key];
        const label = options.title || route.name;

        return (
          <TouchableOpacity
            key={route.key}
            onPress={() => navigation.navigate(route.name)}
            style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}
          >
            <Text style={{ color: 'white', fontSize: 18 }}>{label}</Text>
          </TouchableOpacity>
        );
      })}
    </View>
  );
};

export default function App() {
  return (
    <NavigationContainer>
      <BottomNavigator.Navigator initialRouteName="Home" screenOptions={{ headerShown: false,
                                                                          tabBarActiveTintColor: 'black', 
                                                                          tabBarInactiveTintColor: '#8c8b88', 
                                                                          tabBarStyle: {
                                                                          backgroundColor: '#9F0000', }, }}
      >

        <BottomNavigator.Screen name="Home" component={HomePage} options={{
            tabBarIcon: ({ color }) => (
              <Ionicons name="home" size={26} color={color} />
            ),
            tabBarLabelStyle: { fontSize: 12 }
          }}/>

        <BottomNavigator.Screen name="Info" component={InfoPage} options={{
            tabBarIcon: ({ color }) => (
              <Ionicons name="information-circle" size={26} color={color} />
            ),
            tabBarLabelStyle: { fontSize: 12 }
          }}/>

        <BottomNavigator.Screen name="Profile" component={ProfilePage} options={{
            tabBarIcon: ({ color }) => (
              <Ionicons name="person" size={26} color={color} />
            ),
            tabBarLabelStyle: { fontSize: 12 }
          }}/>

        <BottomNavigator.Screen name="Settings" component={SettingsPage} options={{
            tabBarIcon: ({ color }) => (
              <Ionicons name="settings" size={26} color={color} />
            ),
            tabBarLabelStyle: { fontSize: 12 }
          }}/>
          
      </BottomNavigator.Navigator>
    </NavigationContainer>
  );
}