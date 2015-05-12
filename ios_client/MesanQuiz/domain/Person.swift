//
//  Person.swift
//  MesanQuiz
//
//  Created by Tore Brandtzæg on 12.05.15.
//  Copyright (c) 2015 Mesan. All rights reserved.
//

import Foundation

class Person {
    var fullName: String
    var shortName: String
    var profileImageUrl: String
    
    init(fullName: String, shortName: String, profileImageUrl: String) {
        self.fullName = fullName
        self.shortName = shortName
        self.profileImageUrl = profileImageUrl
    }
}