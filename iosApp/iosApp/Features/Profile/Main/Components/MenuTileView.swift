//
//  MenuTileView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 23.03.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct MenuTileView<Route>: View where Route : View {
    var text: String
    var icon: String
    var color: Color
    var route: Route
    
    init(text: String, icon: String, color: Color, @ViewBuilder route: () -> Route) {
        self.text = text
        self.icon = icon
        self.color = color
        self.route = route()
    }
    
    var body: some View {
        NavigationLink(destination: route) {
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
            }.padding(.horizontal, 20)
        }.buttonStyle(PlainButtonStyle())
    }
}

struct MenuTileView_Previews: PreviewProvider {
    static var previews: some View {
        MenuTileView(
            text: "Профиль",
            icon: "user_icon_96",
            color: Color(hex: 0xFF68D676),
            route: {
                Text("lol")
            }
        )
    }
}
