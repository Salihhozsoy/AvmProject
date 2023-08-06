package com.example.avmproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

object Database {

    val users = listOf(
        User(1, "salih@gmail", "123"),
        User(2, "salih@gmail", "salih"),
    )

    val stores = mutableListOf(
        Store(
            1,
            "Lc Waikiki",
            "https://seeklogo.com/images/L/lc-waikiki-logo-C0472C400B-seeklogo.com.png",
            "A Block",
            "2.Floor",
            "Clothes Store"
        ),
        Store(
            2,
            "Defacto",
            "https://www.lamp83.com.tr/wp-content/uploads/2020/08/defacto-logo.png",
            "B Block",
            "1.Floor",
            "Clothes Store"
        ),
        Store(
            3,
            "Mavi Jeans",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d6/Mavi_Jeans_Logo.svg/2560px-Mavi_Jeans_Logo.svg.png",
            "C Block",
            "3.Floor",
            "Clothes Store"
        ),
        Store(
            4,
            "Avva",
            "https://www.mngavm.com/wp-content/uploads/2022/12/ss-logo-avva-min.png",
            "D Block",
            "2.Floor",
            "Clothes Store"
        ),
        Store(
            5,
            "Lufian",
            "https://seeklogo.com/images/L/lufian-denim-sports-wear-logo-BEE0E4C99C-seeklogo.com.png",
            "A Block",
            "1.Floor",
            "Clothes Store"
        ),
        Store(
            6,
            "Koton",
            "https://loncapazar.s3.eu-north-1.amazonaws.com/vendor/Koton/logo/1686683571700.webp",
            "B Block",
            "3.Floor",
            "Clothes Store"
        ),
        Store(
            7,
            "Lacoste",
            "https://seeklogo.com/images/L/lacoste-logo-84D352062C-seeklogo.com.png",
            "C Block",
            "3.Floor",
            "Clothes Store"
        ),
        Store(
            8,
            "Beymen",
            "https://www.astajans.com/Upload/urunler-upload/img_172.png",
            "D Block",
            "2.Floor",
            "Clothes Store"
        ),
        Store(
            9,
            "Vakko",
            "https://fbfad05d.cdn.akinoncloud.com/static_omnishop/vn-133/img/vakko-logo-w.png",
            "A Block",
            "1.Floor",
            "Clothes Store"
        ),
        Store(
            10,
            "Decathlon",
            "https://fbfad05d.cdn.akinoncloud.com/static_omnishop/vn-133/img/vakko-logo-w.png",
            "A Block",
            "1.Floor",
            "All Category"
        ),
        Store(
            11,
            "Burger King",
            "https://fbfad05d.cdn.akinoncloud.com/static_omnishop/vn-133/img/vakko-logo-w.png",
            "A Block",
            "1.Floor",
            "Fast Food"
        ),
        Store(
            12,
            "Tavuk Dünyası",
            "https://fbfad05d.cdn.akinoncloud.com/static_omnishop/vn-133/img/vakko-logo-w.png",
            "A Block",
            "1.Floor",
            "Chicken Store"
        ),
        Store(
            13,
            "Destan Restaurant",
            "https://fbfad05d.cdn.akinoncloud.com/static_omnishop/vn-133/img/vakko-logo-w.png",
            "A Block",
            "1.Floor",
            "Handmade Meals"
        ),
        Store(
            14,
            "Pizza2Go",
            "https://fbfad05d.cdn.akinoncloud.com/static_omnishop/vn-133/img/vakko-logo-w.png",
            "A Block",
            "1.Floor",
            "Pizza Store"
        ),

        )

    val addStoreList = listOf(
        AddStore(1, "https://www.forumistanbul.com.tr/media/image/adidaslogo.jpg"),
        AddStore(2, "https://www.forumistanbul.com.tr/media/image/adl.png"),
        AddStore(3, "https://www.forumistanbul.com.tr/media/image/Altinyildiz.jpg"),
        AddStore(4, "https://www.forumistanbul.com.tr/magaza/arcelik"),
        AddStore(5, "https://www.forumistanbul.com.tr/media/image/5SIKXGQL3AEJEQ.jpg"),
        AddStore(6, "https://www.forumistanbul.com.tr/magaza/bambi"),
        AddStore(7, "https://www.forumistanbul.com.tr/media/image/bershka.jpg"),
        AddStore(8, "https://www.forumistanbul.com.tr/media/image/Boyner%20Logo%20640x360%20(1).png"),
        AddStore(9, "https://www.forumistanbul.com.tr/media/image/bosch.jpg"),
        AddStore(10, "https://www.forumistanbul.com.tr/media/image/15PEBPCV8698W8.jpg"),
        AddStore(11, "https://www.forumistanbul.com.tr/media/image/IH2261WA2464PC.png"),
        AddStore(12, "https://www.forumistanbul.com.tr/media/image/colins.png"),
        AddStore(13, "https://www.forumistanbul.com.tr/media/image/dr.png"),
        AddStore(14, "https://www.forumistanbul.com.tr/media/image/dagi.png"),
        AddStore(15, "https://www.forumistanbul.com.tr/media/image/derimod.jpg"),
        AddStore(16, "https://www.forumistanbul.com.tr/media/image/denizbank.jpg"),
        AddStore(17, "https://www.forumistanbul.com.tr/media/image/Z4W4UXBB600YJ4.jpg"),
        AddStore(18, "https://www.forumistanbul.com.tr/media/image/ebebek.jpg"),
        AddStore(19, "https://www.forumistanbul.com.tr/media/image/elle.jpg"),
        AddStore(20, "https://www.forumistanbul.com.tr/media/image/english-home.png"),

        )
}

data class User(val id: Int, val email: String, val password: String)

@Parcelize
data class Store(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val block: String,
    val floor: String,
    val description: String
) :
    Parcelable

data class AddStore(val id: Int, val addStoreImage: String)

sealed class LoginState {
    object Idle : LoginState()
    object Success : LoginState()
    object Error : LoginState()
}

sealed class StoreListState {

    object Idle : StoreListState()
    class Result(val stores: List<Store>) : StoreListState()
}

sealed class AddStoreState {

    object Idle : AddStoreState()
    class Add(val store: Store) : AddStoreState()
    object Intent : AddStoreState()
}

sealed class GetAddStoreState {

    object Idle : GetAddStoreState()
    object Loading : GetAddStoreState()
    class Result(val storeImage: List<AddStore>) : GetAddStoreState()
}
