//
//  MenuTileView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 23.03.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct MenuTileView<Content>: View where Content : View {
    var text: String
    var icon: String
    var color: Color
    var onTap: () -> ()
    var suffix: Content
    
    init(text: String, icon: String, color: Color, onTap: @escaping () -> (),  @ViewBuilder suffix: () -> Content = { EmptyView() as! Content }) {
        self.text = text
        self.icon = icon
        self.color = color
        self.onTap = onTap
        self.suffix = suffix()
    }
    
    var body: some View {
        Button(action: { onTap() }) {
            HStack(spacing: 15) {
                Image(icon)
                    .resizable()
                    .padding(4)
                    .background(RoundedRectangle(cornerRadius: 5).fill(color))
                    .frame(width: 32, height: 32)
                
                Text(text)
                    .font(.custom("AvenirNext-Regular", size: 16))
                    .foregroundColor(Color.textPrimary)
                
                Spacer()
                
                suffix
            }.padding(.horizontal, 20)
        }.buttonStyle(PlainButtonStyle())
    }
}

struct MenuTileView_Previews: PreviewProvider {
    static var previews: some View {
        MenuTileView<EmptyView>(
            text: "Профиль",
            icon: "user_icon_96",
            color: Color(hex: 0xFF68D676),
            onTap: {  }
        )
    }
}
