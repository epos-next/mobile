//
//  AboutDevsScreenView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 24.03.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct AboutDevsScreenView: View {
    var body: some View {
        ScrollView {
            VStack(spacing: 30) {
                ProfileHeaderView(
                    text: "О Разработчиках",
                    color: Color(hex: 0xFF83D4FC),
                    icon: "dev_icon"
                )
                
                VStack(alignment: .leading, spacing: 20) {
                    CreatorView()
                    ThanksView()
                    OpenSourceView()
                    ContributeView()
                }.padding(.horizontal, 20)
                
            }
        }.navigationBarHidden(true)
        .navigationBarBackButtonHidden(true)
        .ignoresSafeArea()
    }
}

private struct CreatorView: View {
    var body: some View {
        Link(destination: URL(string: "https://zotov.dev")!) {
            HStack(spacing: 15) {
                Image("yaroslav_zotov")
                    .resizable()
                    .frame(width: 42, height: 42)
                
                VStack(alignment: .leading, spacing: 0) {
                    Text("Ярослав Зотов")
                        .font(.custom("AvenirNext-Regular", size: 16))
                        .foregroundColor(.textPrimary)
                    
                    Text("Создатель")
                        .font(.custom("AvenirNext-Regular", size: 13))
                        .foregroundColor(.secondary)
                }
                
                Spacer()
            }
        }
    }
}

private struct ThanksView: View {
    var body: some View {
        Text("Огромное спасибо Виталию Сухоплечеву и Василию Гайсену за помощь в создании, и [grahacaesara](https://dribbble.com/shots/6853761-Get-Study-Dashboard-App) за вдохновение в дизайне")
            .font(.custom("AvenirNext-Regular", size: 14))
            .foregroundColor(.secondary)
    }
}

private struct OpenSourceView: View {
    var body: some View {
        Text("Это open source проект. Вы можете найти исходники на нашем [гитхабе](https://github.com/epos-next)")
            .font(.custom("AvenirNext-Regular", size: 14))
            .foregroundColor(.secondary)
    }
}

private struct ContributeView: View {
    var body: some View {
        Text("Понравился проект? Вы можете присоединитесь к нашей команду! Напишите в телеграм [@zotovy](https://t.me/zotovy)")
            .font(.custom("AvenirNext-Regular", size: 14))
            .foregroundColor(.secondary)
    }
}

struct AboutDevsScreenView_Previews: PreviewProvider {
    static var previews: some View {
        AboutDevsScreenView()
    }
}
