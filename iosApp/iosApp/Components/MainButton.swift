//
//  MainButton.swift
//  iosApp
//
//  Created by Ярослав Зотов on 19.12.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct MainButtonStyle: ButtonStyle {
    
    let isDisabled: Bool
    
    func makeBody(configuration: Self.Configuration) -> some View {
        return configuration.label
            .frame(height: 51)
            .frame(maxWidth: .infinity)
            .foregroundColor(Color.white)
            .background(isDisabled ? Color.contrast.opacity(0.3) : configuration.isPressed ? Color.contrast.opacity(0.9) : Color.contrast)
            .cornerRadius(6)
            .overlay(RoundedRectangle(cornerRadius: 10).stroke(Color.contrast, lineWidth: 1))
            .font(Font.system(size: 19, weight: .semibold))
            .scaleEffect(configuration.isPressed ? 0.99 : 1.0)
    }
}

struct MainButton: View {
    var isDisabled: Bool = false
    let content: String
    var action: () -> ()
    
    public init (_ content : String, action: @escaping () -> ()) {
        self.content = content
        self.action = action
    }
    
    var body: some View {
        Button(action: action) {
            Text(content)
        }
        .buttonStyle(MainButtonStyle(isDisabled: isDisabled))
        
    }
}

struct MainButton_Previews: PreviewProvider {
    static var previews: some View {
        MainButton("Tap me!", action: {})
            .padding(10)
    }
}
