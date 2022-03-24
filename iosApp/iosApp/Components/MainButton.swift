//
//  MainButton.swift
//  iosApp
//
//  Created by Ярослав Зотов on 19.12.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct MainButtonStyle: ButtonStyle {
    @Environment(\.colorScheme) var colorScheme
    let isDisabled: Bool
    
    func makeBody(configuration: Self.Configuration) -> some View {
        let bg = isDisabled
            ? colorScheme == .light ? Color.lightPrimary : Color.white.opacity(0.1)
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
    let content: String
    var state: ButtonState
    var action: () -> ()
    
    public init (_ content : String, action: @escaping () -> (), state: ButtonState = .idle) {
        self.content = content
        self.action = action
        self.state = state
    }
    
    private let animation: AnyTransition = .asymmetric(
        insertion: .move(edge: .top).combined(with: .opacity),
        removal: .move(edge: .bottom).combined(with: .opacity)
    )
    
    var body: some View {
        Button(action: {
            if state != .loading && state != .disabled { action() }
        }) {
            ZStack {
                if state == .loading {
                    ProgressView()
                        .progressViewStyle(CircularProgressViewStyle(tint: Color.white))
                        .transition(animation)
                }
                else if state == .done {
                    Image("tick")
                        .resizable()
                        .frame(width: 18, height: 18)
                        .transition(animation)
                }
                else {
                    Text(content)
                        .transition(animation)
                }
            }
        }
        .buttonStyle(MainButtonStyle(isDisabled: state == .disabled))
        
    }
}

public enum ButtonState: Int {
    case idle = 1, loading = 2, done = 3, disabled = 4
}

struct MainButton_Previews: PreviewProvider {
    static var previews: some View {
        MainButton("Tap me!", action: {})
            .padding(10)
    }
}
