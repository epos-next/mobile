//
//  HeaderView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 23.03.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct ProfileHeaderView: View {
    var text: String
    var color: Color
    var icon: String
    
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    @Environment(\.colorScheme) var colorScheme
    
    @State private var titleAnimation = false
    
    var body: some View {
        VStack(alignment: .leading) {
            Button(action: { presentationMode.wrappedValue.dismiss() }) {
                Image("arrow_back_white")
                    .resizable()
                    .frame(width: 28, height: 28)
            }.padding(.top, 40)
            Spacer()
            
            HStack {
                Image(icon)
                    .resizable()
                    .foregroundColor(colorScheme == .light ? Color.white : color)
                    .frame(width: 48, height: 48)
                    .padding(.trailing, 10)
                
                if titleAnimation {
                    Text(text)
                        .font(.custom("AvenirNext-Medium", size: 24))
                        .foregroundColor(Color.white)
                        .transition(
                            .move(edge: .bottom)
                            .combined(with: .opacity)
                            .animation(.spring().delay(0.2)))
                        .animation(.spring().delay(0.2))
                }
                
                Spacer()
            }
        }
        .frame(height: 228)
        .padding(20)
        .background(colorScheme == .light ? color : Color.white.opacity(0.05))
        .onAppear {
            withAnimation(.spring()) {
                titleAnimation = true
            }
        }
    }
}

struct HeaderView_Previews: PreviewProvider {
    static var previews: some View {
        ProfileHeaderView(
            text: "Профиль",
            color: Color(hex: 0xFF68D676),
            icon: "user_icon_96"
        )
    }
}
