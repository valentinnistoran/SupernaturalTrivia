import React from 'react';
import { View, StyleSheet, ImageBackground, } from 'react-native';

function InfoPage() {
  return (
    <View style={styles.container}>
      <ImageBackground source={require('../images/VetalaLore.jpg')} style={styles.background_image} resizeMode="contain"/>
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
});

export default InfoPage;