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
        let bg = isDisabled
            ? Color.lightPrimary
            : configuration.isPressed
                ? Color.contrast.opacity(0.9)
                : Color.contrast
        
        return configuration.label
            .frame(height: 51)
            .frame(maxWidth: .infinity)
            .foregroundColor(Color.white)
            .background(bg)
            .cornerRadius(10)
            .font(Font.system(size: 19, weight: .semibold))
            .scaleEffect(configuration.isPressed ? 0.99 : 1.0)
    }
}

struct MainButton: View {
    var isDisabled: Bool = false
    var isLoading: Bool = false
    let content: String
    var action: () -> ()
    
    public init (_ content : String, action: @escaping () -> (), isDisabled: Bool = false, isLoading: Bool = false) {
        self.content = content
        self.action = action
        self.isDisabled = isDisabled
        self.isLoading = isLoading
    }
    
    var body: some View {
        Button(action: {
            if !isLoading && !isDisabled { action() }
        }) {
            if isLoading {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle(tint: Color.white))
            }
            else {
                Text(content)
            }
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
