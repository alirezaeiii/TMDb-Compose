package com.android.sample.tmdb.ui.detail

import androidx.annotation.StringRes
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.OpenInNew
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.android.sample.tmdb.R
import com.android.sample.tmdb.domain.model.*
import com.android.sample.tmdb.ui.common.BottomArcShape
import com.android.sample.tmdb.ui.theme.GetVibrantColorFromPoster
import com.android.sample.tmdb.ui.theme.imageTint
import com.android.sample.tmdb.utils.CircleTopCropTransformation
import com.android.sample.tmdb.utils.dpToPx
import com.android.sample.tmdb.utils.openInChromeCustomTab
import com.android.sample.tmdb.utils.springAnimation

val LocalVibrantColor =
    compositionLocalOf<Animatable<Color, AnimationVector4D>> { error("No vibrant color defined") }

@Composable
fun <T : TMDbItemDetails> DetailScreen(detailWrapper: DetailWrapper<T>, upPress: () -> Unit) {
    val defaultTextColor = MaterialTheme.colors.onBackground
    val vibrantColor = remember { Animatable(defaultTextColor) }
    CompositionLocalProvider(
        LocalVibrantColor provides vibrantColor,
    ) {
        ConstraintLayout(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
                .verticalScroll(rememberScrollState())
        ) {
            val (appbar, backdrop, poster, title, originalTitle, genres, specs, rateStars, tagline, overview) = createRefs()
            val (castSection, crewSection, space) = createRefs()
            val startGuideline = createGuidelineFromStart(16.dp)
            val endGuideline = createGuidelineFromEnd(16.dp)

            detailWrapper.details.posterPath?.let {
                GetVibrantColorFromPoster(
                    it,
                    LocalVibrantColor.current
                )
            }
            detailWrapper.details.backdropPath?.let {
                Backdrop(
                    backdropUrl = it,
                    detailWrapper.details.title,
                    Modifier.constrainAs(backdrop) {})
            }
            val posterWidth = 160.dp
            AppBar(
                homepage = detailWrapper.details.homepage,
                upPress = upPress,
                modifier = Modifier
                    .requiredWidth(posterWidth * 2.2f)
                    .constrainAs(appbar) { centerTo(poster) }
                    .offset(y = 24.dp)
            )
            Poster(
                detailWrapper.details.posterPath,
                detailWrapper.details.title,
                Modifier
                    .zIndex(17f)
                    .width(posterWidth)
                    .height(240.dp)
                    .constrainAs(poster) {
                        centerAround(backdrop.bottom)
                        linkTo(startGuideline, endGuideline)
                    }
            )
            Text(
                text = detailWrapper.details.title,
                style = MaterialTheme.typography.h1.copy(
                    fontSize = 26.sp,
                    letterSpacing = 3.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .constrainAs(title) {
                        top.linkTo(poster.bottom, 8.dp)
                        linkTo(startGuideline, endGuideline)
                    }
            )
            if (detailWrapper.details.title != detailWrapper.details.originalTitle) {
                Text(
                    text = "(${detailWrapper.details.originalTitle})",
                    style = MaterialTheme.typography.subtitle2.copy(
                        fontStyle = FontStyle.Italic,
                        letterSpacing = 2.sp
                    ),
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .constrainAs(originalTitle) {
                            top.linkTo(title.bottom)
                            linkTo(startGuideline, endGuideline)
                        }
                )
            } else {
                Spacer(
                    modifier = Modifier.constrainAs(originalTitle) {
                        top.linkTo(title.bottom)
                        linkTo(startGuideline, endGuideline)
                    }
                )
            }

            GenreChips(
                detailWrapper.details.genres.take(4),
                modifier = Modifier.constrainAs(genres) {
                    top.linkTo(originalTitle.bottom, 16.dp)
                    linkTo(startGuideline, endGuideline)
                }
            )

            TMDbItemFields(
                detailWrapper.details,
                modifier = Modifier.constrainAs(specs) {
                    top.linkTo(genres.bottom, 12.dp)
                    linkTo(startGuideline, endGuideline)
                }
            )

            RateStars(
                detailWrapper.details.voteAverage,
                modifier = Modifier.constrainAs(rateStars) {
                    top.linkTo(specs.bottom, 12.dp)
                    linkTo(startGuideline, endGuideline)
                }
            )

            Text(
                text = detailWrapper.details.tagline,
                color = LocalVibrantColor.current.value,
                style = MaterialTheme.typography.body1.copy(
                    letterSpacing = 2.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .constrainAs(tagline) {
                        top.linkTo(rateStars.bottom, 32.dp)
                    }
            )

            Text(
                text = detailWrapper.details.overview,
                style = MaterialTheme.typography.body2.copy(
                    letterSpacing = 2.sp,
                    lineHeight = 30.sp,
                    fontFamily = FontFamily.SansSerif
                ),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .constrainAs(overview) {
                        top.linkTo(tagline.bottom, 8.dp)
                        linkTo(startGuideline, endGuideline)
                    }
            )
            CreditSection(
                items = detailWrapper.cast,
                headerResId = R.string.cast,
                itemContent = { item, _ -> Person(item, Modifier.width(140.dp)) },
                modifier = Modifier.constrainAs(castSection) {
                    top.linkTo(overview.bottom, 16.dp)
                    linkTo(startGuideline, endGuideline)
                }
            )
            CreditSection(
                items = detailWrapper.crew,
                headerResId = R.string.crew,
                itemContent = { item, _ -> Person(item, Modifier.width(140.dp)) },
                modifier = Modifier.constrainAs(crewSection) {
                    top.linkTo(castSection.bottom, 16.dp)
                    linkTo(startGuideline, endGuideline)
                }
            )

            Spacer(
                modifier = Modifier
                    .windowInsetsBottomHeight(WindowInsets.navigationBars)
                    .constrainAs(space) {
                        top.linkTo(crewSection.bottom)
                    }
            )
        }
    }
}

@Composable
private fun Backdrop(backdropUrl: String, tmdbItemName: String, modifier: Modifier) {
    Card(
        elevation = 16.dp,
        shape = BottomArcShape(arcHeight = 120.dpToPx()),
        backgroundColor = LocalVibrantColor.current.value.copy(alpha = 0.1f),
        modifier = modifier.height(360.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(data = backdropUrl)
                .crossfade(1500).build(),
            contentScale = ContentScale.FillHeight,
            contentDescription = tmdbItemName,
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun AppBar(modifier: Modifier, homepage: String?, upPress: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        val vibrantColor = LocalVibrantColor.current.value
        val scaleModifier = Modifier.scale(1.1f)
        IconButton(onClick = { upPress.invoke() }) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "back",
                tint = vibrantColor,
                modifier = scaleModifier
            )
        }
        if (!homepage.isNullOrBlank()) {
            val context = LocalContext.current
            IconButton(onClick = { homepage.openInChromeCustomTab(context, vibrantColor) }) {
                Icon(
                    Icons.Rounded.OpenInNew,
                    contentDescription = "open",
                    tint = vibrantColor,
                    modifier = scaleModifier
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Poster(posterUrl: String?, tmdbItemName: String, modifier: Modifier) {
    val isScaled = remember { mutableStateOf(false) }
    val scale =
        animateFloatAsState(
            targetValue = if (isScaled.value) 2.2f else 1f,
            animationSpec = springAnimation
        ).value

    Card(
        elevation = 24.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.scale(scale),
        onClick = { isScaled.value = !isScaled.value }
    ) {
        AsyncImage(
            model = posterUrl,
            contentDescription = tmdbItemName,
            contentScale = ContentScale.FillHeight
        )
    }
}

@Composable
private fun GenreChips(genres: List<Genre>, modifier: Modifier) {
    Row(
        modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        genres.map(Genre::name).forEachIndexed { index, name ->
            Text(
                text = name.orEmpty(),
                style = MaterialTheme.typography.subtitle1.copy(letterSpacing = 2.sp),
                modifier = Modifier
                    .border(1.25.dp, LocalVibrantColor.current.value, RoundedCornerShape(50))
                    .padding(horizontal = 6.dp, vertical = 3.dp)
            )

            if (index != genres.lastIndex) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
private fun TMDbItemFields(tmdbItemDetails: TMDbItemDetails, modifier: Modifier) {
    Row(horizontalArrangement = Arrangement.spacedBy(20.dp), modifier = modifier) {
        val context = LocalContext.current
        tmdbItemDetails.releaseDate?.let { TMDbItemField(context.getString(R.string.release_date), it) }
        TMDbItemField(context.getString(R.string.vote_average), tmdbItemDetails.voteAverage.toString())
        TMDbItemField(context.getString(R.string.votes), tmdbItemDetails.voteCount.toString())
    }
}

@Composable
private fun TMDbItemField(name: String, value: String) {
    Column {
        Text(
            text = name,
            style = MaterialTheme.typography.subtitle2.copy(fontSize = 13.sp, letterSpacing = 1.sp),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.SemiBold),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 4.dp)
        )
    }
}

@Composable
private fun RateStars(voteAverage: Double, modifier: Modifier) {
    Row(modifier.padding(start = 4.dp)) {
        val maxVote = 10
        val starCount = 5
        repeat(starCount) { starIndex ->
            val voteStarCount = voteAverage / (maxVote / starCount)
            val asset = when {
                voteStarCount >= starIndex + 1 -> Icons.Filled.Star
                voteStarCount in starIndex.toDouble()..(starIndex + 1).toDouble() -> Icons.Filled.StarHalf
                else -> Icons.Filled.StarOutline
            }
            Icon(
                imageVector = asset,
                contentDescription = null,
                tint = LocalVibrantColor.current.value
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}

@Composable
private fun <T : Credit> CreditSection(
    items: List<T>,
    @StringRes headerResId: Int,
    itemContent: @Composable (T, Int) -> Unit,
    modifier: Modifier
) {
    if(items.isNotEmpty()) {
        Column(modifier = modifier.fillMaxWidth()) {
            SectionHeader(headerResId, items.size)
            LazyRow(
                modifier = Modifier.testTag(LocalContext.current.getString(headerResId)),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(
                    count = items.size,
                    itemContent = { index ->
                        itemContent(items[index], index)
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                )
            }
        }
    }
}

@Composable
private fun SectionHeader(
    @StringRes headerResId: Int,
    count: Int,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(headerResId),
            color = LocalVibrantColor.current.value,
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(4.dp)
        ) {
            Text(
                text = stringResource(R.string.see_all, count),
                color = LocalVibrantColor.current.value,
                style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(end = 4.dp)
            )
            Icon(
                Icons.Filled.ArrowForward,
                contentDescription = stringResource(R.string.see_all),
                tint = LocalVibrantColor.current.value
            )
        }
    }
}

@Composable
fun Person(person: Credit, modifier: Modifier = Modifier) {
    Column(
        modifier.padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(shape = CircleShape, elevation = 8.dp, modifier = Modifier.size(120.dp)) {
            val request = ImageRequest.Builder(LocalContext.current)
                .data(person.profileUrl)
                .crossfade(true)
                .transformations(CircleTopCropTransformation())
                .build()
            val placeholderPainter = rememberVectorPainter(person.gender.placeholderIcon)
            val painter =
                rememberAsyncImagePainter(
                    model = request,
                    error = placeholderPainter,
                    placeholder = placeholderPainter
                )
            val colorFilter = when (painter.state) {
                is AsyncImagePainter.State.Error, is AsyncImagePainter.State.Loading -> ColorFilter.tint(
                    MaterialTheme.colors.imageTint
                )
                else -> null
            }
            Image(
                painter = painter,
                colorFilter = colorFilter,
                contentDescription = stringResource(
                    id = R.string.person_content_description,
                    person.name,
                    person.role
                ),
                contentScale = ContentScale.FillHeight
            )
        }
        Text(
            text = person.name,
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.SemiBold),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = person.role,
            style = MaterialTheme.typography.subtitle2.copy(
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Italic
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}
