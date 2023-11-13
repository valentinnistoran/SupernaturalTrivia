import React from 'react';
import {View, StyleSheet, ImageBackground, Image, Text, TouchableOpacity } from 'react-native';
import { useState } from 'react';
import { LinearGradient } from 'expo-linear-gradient';

const InvisibleContainer = ({ children, containerStyle }) => {
  return <View style={[styles.invisibleContainer, containerStyle]}>{children}</View>;
};

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
      <ImageBackground source={require('../images/triviaBackground.png')} style={styles.backgroundImage} resizeMode="contain"/>
      <TouchableOpacity style={styles.quitButton}>
        <LinearGradient colors={['transparent', 'rgba(0, 0, 0, 0.35)']} style={styles.gradient}/>
        <Text style={styles.auxiliaryText}>Quit</Text>
      </TouchableOpacity>
      <TouchableOpacity style={styles.skipButton}>
        <LinearGradient colors={['transparent', 'rgba(0, 0, 0, 0.35)']} style={styles.gradient}/>
        <Text style={styles.auxiliaryText}>Skip</Text>
      </TouchableOpacity>
      <Text style={styles.questionIndex}>1/10</Text>
      <View style={styles.timerBody}></View>
      <Image source={require('../images/dean_judgind_us.png')} style={styles.questionImage} resizeMode="contain"/>
      <Text style={styles.questionText}>{quizData[currentQuestion]?.question}</Text>
      <View style={styles.optionsContainer}>
        {quizData[currentQuestion]?.options.map((item) => {
            return <TouchableOpacity style={styles.optionButton}>
                      <LinearGradient colors={['transparent', 'rgba(0, 0, 0, 0.35)']} style={styles.gradient}/>
                      <Text style={styles.optionText}>{item}</Text>
                   </TouchableOpacity>
        })}
      </View>
      <TouchableOpacity style={styles.nextButton}>
        <LinearGradient colors={['transparent', 'rgba(0, 0, 0, 0.35)']} style={styles.gradient}/>
        <Text style={styles.auxiliaryText}>Next</Text>
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
  backgroundImage: {
    flex: 1,
    aspectRatio: 1,
  },
  quitButton: {
    position: 'absolute',
    top: '6%',
    left: '10%',
    backgroundColor: '#CA7629',
    width: 100,
    height: 30,
    borderRadius: 5,
    justifyContent: 'center',
    alignItems: 'center',
    borderWidth: 1,
    borderColor: 'black',
  },
  skipButton: {
    position: 'absolute',
    top: '6%',
    right: '10%',
    backgroundColor: '#CA7629',
    width: 100,
    height: 30,
    borderRadius: 5,
    justifyContent: 'center',
    alignItems: 'center',
    borderWidth: 1,
    borderColor: 'black',
  },
  questionIndex: {
    position: 'absolute',
    top: '15%',
    fontSize: 25,
    color: 'white',
    fontFamily: 'serif',
    textAlign: 'center',
    textShadowColor: 'black',
    textShadowOffset: { width: 1, height: 1 },
    textShadowRadius: 1,
  },
  timerBody: {
    position: 'absolute',
    top: '22%',
    backgroundColor: '#CA7629',
    width: 320,
    height: 40,
    borderRadius: 15,
    borderWidth: 1,
    borderColor: 'black',
  },
  questionImage: {
    position: 'absolute',
    top: '32%',
    width: '80%',
    height: '20%',
    borderRadius: 15,
  },
  questionText: {
    position: 'absolute',
    top: '55%',
    fontSize: 18,
    color: 'white',
    fontFamily: 'serif',
    textAlign: 'center',
    width: 400
  },
  optionsContainer: {
    position: 'absolute',
    top: '65%',
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
    flexWrap: 'wrap',
  },
  optionButton: {
    backgroundColor: '#CA7629',
    width: '37%',
    height: 60,
    borderRadius: 5,
    justifyContent: 'center',
    alignItems: 'center',
    marginHorizontal: 10,
    marginVertical: 13,
    borderWidth: 1,
    borderColor: 'black',
  },
  optionText: {
    fontSize: 15,
    color: 'white',
    fontFamily: 'serif',
    textAlign: 'center',
    textShadowColor: 'black',
    textShadowOffset: { width: 1, height: 1 },
    textShadowRadius: 1,
  },
  nextButton: {
    position: 'absolute',
    bottom: '6%',
    right: '10%',
    backgroundColor: '#CA7629',
    width: 100,
    height: 30,
    borderRadius: 5,
    justifyContent: 'center',
    alignItems: 'center',
    borderWidth: 1,
    borderColor: 'black',
  },
  auxiliaryText: {
    fontSize: 13,
    color: 'white',
    fontFamily: 'serif',
    textAlign: 'center',
    textShadowColor: 'black',
    textShadowOffset: { width: 1, height: 1 },
    textShadowRadius: 1,
  },
  gradient: {
    position: 'absolute',
    width: '100%',
    height: '100%',
  },
});


export default TriviaPage;