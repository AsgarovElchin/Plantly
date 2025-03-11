package asgarov.elchin.plantly.feature_explore.domain.model

data class Plant(
    val id:String?,
    val imageUrl:String?,
    val commonNames: List<String?>,
    val latinName:String?,
    val family:String?
)
