import React from 'react';
import { View, Text, TouchableOpacity } from 'react-native';
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
      <View style={styles.questionContainer}></View>
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


export default TriviaPage;