//
//  Alternative.swift
//  MesanQuiz
//
//  Created by Anders Ullnæss on 5/12/15.
//  Copyright (c) 2015 Mesan. All rights reserved.
//

import Foundation

class Alternative : Printable {
    var id: Int?
    var questionId: Int?
    var alternative: String?
    var answer: Bool?
    
    var description: String {
        return "{ id: \(id!), questionId: \(questionId!), alternative: \(alternative!), answer: \(answer!) }"
    }
    
    init(id: Int, questionId: Int, alternative: String, answer: Bool) {
        self.id = id
        self.questionId = questionId
        self.alternative = alternative
        self.answer = answer
    }
 
    class func fromJsonArray(jsonAlternatives: NSArray) -> [Alternative]? {
        var alternatives = [Alternative]()
        
        for jsonAlternative in jsonAlternatives {
            if let
                jA = jsonAlternative as? NSDictionary,
                alternative = fromJson(jA)
            {
                alternatives.append(alternative)
            }
        }
        
        return alternatives
    }
    
    class func fromJson(jsonAlternative: NSDictionary) -> Alternative? {
        if let
            id = jsonAlternative["id"] as? Int,
            questionId = jsonAlternative["questionId"] as? Int,
            alternative = jsonAlternative["alternative"] as? String,
            answer = jsonAlternative["answer"] as? Bool
        {
            return Alternative(id: id, questionId: questionId, alternative: alternative, answer: answer)
        }
        
        return nil
    }
}