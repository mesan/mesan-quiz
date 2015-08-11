//
//  Question.swift
//  MesanQuiz
//
//  Created by Anders Ullnæss on 5/12/15.
//  Copyright (c) 2015 Mesan. All rights reserved.
//

import Foundation

class Question : Printable {
    var id: Int?
    var gameId: Int?
    var question: String?
    var alternatives: [Alternative]?
    var subject: String?
    
    var description: String {
        return "{ id: \(id!), gameId: \(gameId!), question: \(question!), alternatives: \(alternatives!) }"
    }
    
    init() {
        
    }
    
    init(id: Int, gameId: Int, question: String, alternatives: [Alternative]) {
        self.id = id
        self.gameId = gameId
        self.question = question
        self.alternatives = alternatives
    }
    
    class func fromJsonArray(jsonQuestions: NSArray) -> [Question]? {
        var questions = [Question]()
        
        for jsonQuestion in jsonQuestions {
            if let
                jQ = jsonQuestion as? NSDictionary,
                question = fromJson(jQ)
            {
                questions.append(question)
            }
        }
        
        return questions
    }
    
    class func fromJson(jsonQuestion: NSDictionary) -> Question? {
        if let
            id                  = jsonQuestion["id"] as? Int,
            gameId              = jsonQuestion["gameId"] as? Int,
            question            = jsonQuestion["question"] as? String,
            jsonAlternatives    = jsonQuestion["alternatives"] as? NSArray,
            alternatives        = Alternative.fromJsonArray(jsonAlternatives)
        {
            return Question(id: id, gameId: gameId, question: question, alternatives: alternatives)
        }
        
        return nil
    }
}
