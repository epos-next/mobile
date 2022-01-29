//
//  AdvertisementComponentView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 29.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AdvertisementComponentView: View {
    var advertisement: Advertisement
    
    init(_ advertisement: Advertisement) {
        self.advertisement = advertisement
    }
    
    var body: some View {
        HStack {
            AdCircle()
            Text(advertisement.content)
                .font(.custom("AvenirNext-Regular", size: 14))
                .foregroundColor(.secondary)
        }
        .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
    }
}

private struct AdCircle: View {
    var body: some View {
        Circle()
            .fill(Color.lightPrimary)
            .frame(width: 5, height: 5)
    }
}

struct AdvertisementComponentView_Previews: PreviewProvider {
    static var previews: some View {
        let ad = Advertisement(
            id: 1,
            content: "Test ad",
            targetDate: Kotlinx_datetimeLocalDateTime.init(
                year: 2022,
                month: Kotlinx_datetimeMonth.february,
                dayOfMonth: 23,
                hour: 15,
                minute: 3,
                second: 0,
                nanosecond: 0
            )
        )
        
        AdvertisementComponentView(ad)
    }
}
