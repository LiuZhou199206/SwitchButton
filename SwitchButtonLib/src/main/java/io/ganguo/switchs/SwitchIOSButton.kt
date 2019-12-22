package io.ganguo.switchs

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet

/**
 * <pre>
 * author : leo
 * time   : 2019/12/03
 * desc   : iOS风格Switch Button
 * <pre>
 * @property iosRightCircleRadius 右侧小圆半径
 * @property iosRightCircleMarginRight 右侧小圆右边距
 * @property iosRightCircleColor 右侧小圆颜色
 * @property iosRightCircleWidth 右侧小圆线条宽度
 * @property iosLeftLineHeight 左边竖线高度
 * @property iosLeftLineWidth 左边竖线宽度
 * @property iosLeftLineMarginLeft 左边竖线左边距
 * @property iosLeftLineColor 左边竖线颜色
 *
 * @property linePath 竖线绘制路径
 * @property circlePath 小圆绘制路径
 * @property linePaint 竖线画笔
 * @property circlePaint 小圆画笔
 */
class SwitchIOSButton : SwitchButton {
    private var iosLeftLineColor: Int = Color.WHITE
    private var iosLeftLineHeight: Int = 20
    private var iosLeftLineWidth: Int =2
    private var iosLeftLineMarginLeft: Int = 24
    private var iosRightCircleColor: Int = Color.DKGRAY
    private var iosRightCircleRadius: Int = 6
    private var iosRightCircleWidth: Int = 2
    private var iosRightCircleMarginRight: Int = iosLeftLineMarginLeft - iosRightCircleRadius

    private var linePath: Path = Path()
    private var circlePath: Path = Path()
    private var linePaint: Paint = Paint()
    private var circlePaint: Paint = Paint()

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initIOSPaint()
    }

    /**
     * 初始化自定义属性
     * @param array TypedArray
     */
    override fun initAttributes(array: TypedArray) {
        super.initAttributes(array)
        iosLeftLineColor = array.getColor(R.styleable.SwitchButton_iosLeftLineColor, iosLeftLineColor)
        iosLeftLineHeight = array.getDimensionPixelOffset(R.styleable.SwitchButton_iosLeftLineHeight, iosLeftLineHeight)
        iosLeftLineWidth = array.getDimensionPixelOffset(R.styleable.SwitchButton_iosLeftLineWidth, iosLeftLineWidth)
        iosLeftLineMarginLeft = array.getDimensionPixelOffset(R.styleable.SwitchButton_iosLeftLineMarginLeft, iosLeftLineMarginLeft)

        iosRightCircleColor = array.getColor(R.styleable.SwitchButton_iosRightCircleColor, iosRightCircleColor)
        iosRightCircleRadius = array.getDimensionPixelOffset(R.styleable.SwitchButton_iosRightCircleRadius, iosRightCircleRadius)
        iosRightCircleWidth = array.getDimensionPixelOffset(R.styleable.SwitchButton_iosRightCircleWidth, iosRightCircleWidth)
        iosRightCircleMarginRight = array.getDimensionPixelOffset(R.styleable.SwitchButton_iosRightCircleMarginRight, iosRightCircleMarginRight)
    }


    /**
     * 初始化画笔
     */
    private fun initIOSPaint() {
        linePaint.style = Paint.Style.FILL
        linePaint.strokeJoin = Paint.Join.ROUND
        linePaint.strokeCap = Paint.Cap.ROUND
        linePaint.color = iosLeftLineColor
        linePaint.isAntiAlias = true
        linePaint.isDither = true

        circlePaint.style = Paint.Style.STROKE
        circlePaint.strokeJoin = Paint.Join.ROUND
        circlePaint.strokeCap = Paint.Cap.ROUND
        circlePaint.color = iosRightCircleColor
        circlePaint.strokeWidth = iosRightCircleWidth.toFloat()
        circlePaint.isAntiAlias = true
        circlePaint.isDither = true
    }


    override fun onDrawToggleTrack(canvas: Canvas) {
        super.onDrawToggleTrack(canvas)
        onDrawLeftLine(canvas)
        onDrawRightCircle(canvas)

    }


    /**
     * 绘制左边竖线
     */
    private fun onDrawLeftLine(canvas: Canvas) {
        linePath.reset()
        var lineRectF = RectF()
        lineRectF.left = iosLeftLineMarginLeft.toFloat()
        lineRectF.right = lineRectF.left + iosLeftLineWidth
        lineRectF.top = (height - iosLeftLineHeight) * 0.5f
        lineRectF.bottom = lineRectF.top + iosLeftLineHeight
        linePath.addRect(lineRectF, Path.Direction.CW)
        canvas.save()
        canvas.drawPath(linePath, linePaint)
        canvas.restore()
    }

    /**
     * 绘制右侧小圆圈
     */
    private fun onDrawRightCircle(canvas: Canvas) {
        circlePath.reset()
        var centerX = width - iosRightCircleMarginRight - iosRightCircleRadius
        var centerY = height * 0.5f
        circlePath.addCircle(centerX.toFloat(), centerY, iosRightCircleRadius.toFloat(), Path.Direction.CW)
        canvas.save()
        canvas.drawPath(circlePath, circlePaint)
        canvas.restore()
    }


}