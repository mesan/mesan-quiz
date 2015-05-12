//
//  Person.swift
//  MesanQuiz
//
//  Created by Tore Brandtzæg on 12.05.15.
//  Copyright (c) 2015 Mesan. All rights reserved.
//

import Foundation

class Person : Printable {
    var fullName: String
    var shortName: String
    var profileImageUrl: String
    
    var description: String {
        return "{ fullName: \(fullName), shortName: \(shortName), profileImageUrl: \(profileImageUrl) }"
    }
    
    init(fullName: String, shortName: String, profileImageUrl: String) {
        self.fullName = fullName
        self.shortName = shortName
        self.profileImageUrl = profileImageUrl
    }
    
    class func fromJson(personDict: NSDictionary) -> Person? {
        if let
            fullName        = personDict["fullName"] as? String,
            shortName       = personDict["shortName"] as? String,
            profileImageUrl = personDict["profileImageUrl"] as? String
        {
                return Person(fullName: fullName, shortName: shortName, profileImageUrl: profileImageUrl)
        }
        
        return nil
    }
}