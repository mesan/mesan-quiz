//
//  Game.swift
//  MesanQuiz
//
//  Created by Tore Brandtzæg on 12.05.15.
//  Copyright (c) 2015 Mesan. All rights reserved.
//

import Foundation

class Game : Printable {
    var id: Int
    var name: String
    var creator: Person
    var topic: String
    var timeLimit: Int
    
    var description: String {
        return "{ id: \(id), name: \(name), creator: \(creator), topic: \(topic), timeLimit: \(timeLimit) }"
    }
    
    init(id: Int, name: String, creator: Person, topic: String, timeLimit: Int) {
        self.id = id
        self.name = name
        self.creator = creator
        self.topic = topic
        self.timeLimit = timeLimit
    }
    
    class func fromJson(jsonGame: NSDictionary) -> Game? {
        if let
            id                  = jsonGame["id"] as? Int,
            name                = jsonGame["name"] as? String,
            creator             = Person.fromJson(jsonGame["creator"] as! NSDictionary),
            topic               = jsonGame["topic"] as? String,
            timeLimit           = jsonGame["timeLimit"] as? Int
        {
                return Game(id: id, name: name, creator: creator, topic: topic, timeLimit: timeLimit)
        }
        
        return nil
    }
}