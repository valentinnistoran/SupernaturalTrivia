import React from 'react';
import { View, StyleSheet, ImageBackground, Image, Text, TouchableOpacity } from 'react-native';
// import { useNavigation } from '@react-navigation/native';
// import TriviaPage from './TriviaPage';


function HomePage({ navigation }) {
  const openTriviaPage = () => {
    navigation.navigate('TriviaPage');
  }
  return (
    <View style={styles.container}>
      <ImageBackground source={require('../images/VetalaLore.jpg')} style={styles.background_image} resizeMode="contain"/>
      <Image source={require('../images/SupernaturalTitleImage.png')} style={styles.title_image} resizeMode="contain"/>
      <Text style={styles.text}>Choose the Difficulty</Text>
      <TouchableOpacity style={styles.easy_button} onPress={openTriviaPage}>
        <Text style={styles.buttonText}>Dad’s Journal</Text>
      </TouchableOpacity>
      <TouchableOpacity style={styles.medium_button} onPress={openTriviaPage}>
        <Text style={styles.buttonText}>Bobby’s Study Room</Text>
      </TouchableOpacity>
      <TouchableOpacity style={styles.hard_button} onPress={openTriviaPage}>
        <Text style={styles.buttonText}>Bunker’s Library</Text>
      </TouchableOpacity>
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
  background_image: {
    flex: 1,
    aspectRatio: 1,
  },
  title_image: {
    position: 'absolute',
    top: '5%',
    width: '90%',
  },
  text: {
    position: 'absolute',
    top: '50%',
    fontSize: 20,
    color: 'white',
    fontFamily: 'serif',
  },
  buttonText: {
    fontSize: 14,
    color: 'white',
    fontFamily: 'serif',
    textAlign: 'center',
  },
  easy_button: {
    position: 'absolute',
    top: '56%',
    backgroundColor: '#215C0C',
    width: 300,
    height: 60,
    borderRadius: 5,
    justifyContent: 'center',
    alignItems: 'center',
  },
  medium_button: {
    position: 'absolute',
    top: '66%',
    backgroundColor: '#CA7629',
    width: 300,
    height: 60,
    borderRadius: 5,
    justifyContent: 'center',
    alignItems: 'center',
  },
  hard_button: {
    position: 'absolute',
    top: '76%',
    backgroundColor: '#9F0000',
    width: 300,
    height: 60,
    borderRadius: 5,
    justifyContent: 'center',
    alignItems: 'center',
  },
});

export default HomePage;