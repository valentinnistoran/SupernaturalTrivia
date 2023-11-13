import React from 'react';
import {View, StyleSheet, ImageBackground, Image, Text, TouchableOpacity } from 'react-native';
import { useState } from 'react';

function TriviaPage() {

  const [currentQuestion, setCurrentQuestion] = useState(0);

  const quizData = [
    {
      question: 'What is the name of the episode from which the above screenshot is from?',
      options: ['Swan Song', 'Fanfiction', 'Baby', 'Reading is Fundamental'],
      answer: 'Fanfiction'
    },
    {
      question: 'What is Sam afraid of?',
      options: ['Clowns','Witches','Angels','Dogs'],
      answer: 'Clowns'

    }
  ]
  return (
    <View style={styles.container}>
      <ImageBackground source={require('../images/triviaBackground.png')} style={styles.background_image} resizeMode="contain"/>
      <View style={styles.questionContainer}></View>
        <Image source={require('../images/dean_judgind_us.png')} style={styles.title_image} resizeMode="contain"/>
        <Text>{quizData[currentQuestion]?.question}</Text>
        {quizData[currentQuestion]?.options.map((item) => {
          return <TouchableOpacity style={styles.optionContainer}>
            <Text style={styles.otionStyle}>{item}</Text>
          </TouchableOpacity>

        })}
      {/* Add content for the Trivia screen */}
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
    width: '20%',
    height: '20%',
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
    marginTop: 10,
    backgroundColor: '#CA7629',
    width: 300,
    height: 50,
    borderRadius: 5,
    justifyContent: 'center',
    alignItems: 'center'
  }
});


export default TriviaPage;