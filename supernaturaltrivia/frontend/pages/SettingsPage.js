import React from 'react';
import { View, StyleSheet, ImageBackground, Image, Text, TouchableOpacity} from 'react-native';

function SettingsPage() {
  return (
    <View style={styles.container}>
      <ImageBackground source={require('../images/VetalaLore.jpg')} style={styles.background_image} resizeMode="contain"/>
      <Text style={styles.title}>Settings Page</Text>
      <TouchableOpacity style={styles.language_button}>
        <Text style={styles.buttonText}>Language</Text>
      </TouchableOpacity>
      <TouchableOpacity style={styles.help_button}>
        <Text style={styles.buttonText}>Help</Text>
      </TouchableOpacity>
      <TouchableOpacity style={styles.about_us_button}>
        <Text style={styles.buttonText}>About Us</Text>
      </TouchableOpacity>
      <TouchableOpacity style={styles.share_button}>
        <Text style={styles.buttonText}>Share</Text>
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
  title: {
    fontSize: 25,
    color: 'white',
    position: 'absolute',
    top: '10%', 
    fontFamily: 'serif',
  },
  language_button: {
    position: 'absolute',
    top: '25%',
    backgroundColor: 'white',
    width: 300,
    height: 50,
    borderRadius: 5,
    justifyContent: 'center',
    alignItems: 'center',
  },
  help_button: {
    position: 'absolute',
    top: '35%',
    backgroundColor: 'white',
    width: 300,
    height: 50,
    borderRadius: 5,
    justifyContent: 'center',
    alignItems: 'center',
  },
  about_us_button: {
    position: 'absolute',
    top: '45%',
    backgroundColor: 'white',
    width: 300,
    height: 50,
    borderRadius: 5,
    justifyContent: 'center',
    alignItems: 'center',
  },
  share_button: {
    position: 'absolute',
    top: '55%',
    backgroundColor: 'white',
    width: 300,
    height: 50,
    borderRadius: 5,
    justifyContent: 'center',
    alignItems: 'center',
  },
});

export default SettingsPage;