//
//  Logo.swift
//  iosApp
//
//  Created by Ярослав Зотов on 19.12.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct LoginLogoView: View {
    var body: some View {
        Image("logo")
            .resizable()
            .frame(width: 65, height: 65, alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/)
    }
}

struct LoginLogo_Previews: PreviewProvider {
    static var previews: some View {
        LoginLogoView()
    }
}
