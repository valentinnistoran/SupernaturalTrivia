import React from 'react';
import { View, StyleSheet, ImageBackground, Image, Text, TouchableOpacity } from 'react-native';

function ProfilePage() {
  return (
<ImageBackground source={require('../images/VetalaLore.jpg')} style={styles.container} resizeMode="contain">
      <Text style={styles.title}>Profile Page</Text>
      <View style={styles.profileContainer}>
        <Image source={require('../images/castiel_profile.png')} style={styles.profileImage} />
        <View style={styles.profileInfo}>
          <Text style={styles.username}>Username: cas'leftWing</Text>
          <Text style={styles.favoriteCharacter}>Favorite Character: Castiel</Text>
        </View>
      </View>
      <Text style={styles.historyText}>Game History</Text>
    </ImageBackground>
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
  profileImage: {
    width: 100, 
    height: 100, 
    borderRadius: 50, 
    marginRight: 10, 
  },
  username: {
    fontSize: 18,
    color: 'white',
    fontFamily: 'serif',
  },
  favoriteCharacter: {
    fontSize: 16,
    color: 'white',
    fontFamily: 'serif',
  },
  historyText: {
    position: 'absolute',
    top: '40%',
    fontSize: 20,
    color: 'white',
    fontFamily: 'serif',
  },
});

export default ProfilePage;