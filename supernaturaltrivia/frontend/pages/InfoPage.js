import React from 'react';
import { View, StyleSheet, ImageBackground, Image, Text, TouchableOpacity} from 'react-native';

function InfoPage() {
  return (
    <View style={styles.container}>
      <ImageBackground source={require('../images/VetalaLore.jpg')} style={styles.background_image} resizeMode="contain"/>
      <Text style={styles.title}>Info Page</Text>
      <Text style={styles.subtitle}>Today's Fun Fact</Text>
      <Text style={styles.infoText}>
        Did you know...
        {"\n"}
        Misha Collins auditioned for a demon
        {"\n"}
        role, but it was later revealed that they
        {"\n"} 
        were introducing angels in the show.
      </Text>
      <Text style={styles.textForButtons}>Show's Official Pages</Text>
      <TouchableOpacity style={styles.insta_button}>
        <Text style={styles.buttonText}>Instagram: @cw_supernatural</Text>
      </TouchableOpacity>
      <TouchableOpacity style={styles.twitter_button}>
        <Text style={styles.buttonText}>Twitter: @cw_spn</Text>
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
  title: {
    fontSize: 25,
    color: 'white',
    position: 'absolute',
    top: '10%', 
    fontFamily: 'serif',
  },
  subtitle: {
    fontSize: 20,
    color: 'white',
    position: 'absolute',
    top: '20%',
    fontFamily: 'serif',
    textAlign: 'left',
  },
  infoText: {
    fontSize: 16,
    color: 'white',
    position: 'absolute',
    top: '30%',
    textAlign: 'left', 
    paddingHorizontal: 20, 
    fontFamily: 'serif',
  },
  textForButtons: {
    position: 'absolute',
    top: '56%',
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
  insta_button: {
    position: 'absolute',
    top: '66%',
    backgroundColor: '#d62976',
    width: 300,
    height: 60,
    borderRadius: 5,
    justifyContent: 'center',
    alignItems: 'center',
  },
  twitter_button: {
    position: 'absolute',
    top: '76%',
    backgroundColor: '#26a7de',
    width: 300,
    height: 60,
    borderRadius: 5,
    justifyContent: 'center',
    alignItems: 'center',
  },
});

export default InfoPage;